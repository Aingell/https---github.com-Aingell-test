$(() => {
  let url = backUrL + "/stock/listById.do";
  $.ajax({
    url: url,
    method: "post",
    success: function (jsonStr) {
      let $origin = $("div.sell").first();
      let $parent = $("div.sellList");
      $(jsonStr).each((index, s) => {
        let sFile = s.sFile;
        let sizeCategoryName = s.sizeCategoryName;
        let sName = s.sName;
        let sGrade = s.sGrade;
        let sReturn = s.sReturn;
        let sNum = s.sNum;

        let $copy = $origin.clone();

        if (sReturn == 1) {
          sReturn = "검수중";
        } else if (sReturn == 2) {
          sReturn = "검수완료";
        } else if (sReturn == 3) {
          sReturn = "판매등록 대기중";
        }
        if (sGrade == null) {
          sGrade = "-";
        }

        let $imgObj = $("<img class='sFile'>"); //태그용 객체를 만듬
        $imgObj.attr("src", "../imgs/" + sFile); //+ ".jpg"
        $copy.find("div.sNum").html(sNum);
        $copy.find("div.sFile").empty().append($imgObj);
        $copy.find("div.sName").html(sName + '<div class="sizeCategoryName">');
        $copy.find("div.sizeCategoryName").html(sizeCategoryName);
        $copy.find("div.sGrade").html(sGrade);
        $copy.find("div.sReturn").html(sReturn);
        $parent.append($copy);
      });
      $origin.hide();
    },
    error: function (xhr) {
      alert(xhr.status);
    },
  });

  //--상세보기 클릭되었을 때 할일 START--
  $("div.sellList").on("click", "div.sell", function (e) {
    let sReturn = $(e.target).parents("div.sell").find("div.sReturn").html();
    if (sReturn == "검수완료") {
      let sNum = $(e.target).parents("div.sell").find("div.sNum").html();
      location.href = "./inventoryDetail.html?sNum=" + sNum;
    } else if (sReturn == "검수중") {
      alert("아직 상품이 검수중입니다.");
    } else if (sReturn == "판매등록 대기중") {
      alert("관리자가 상품을 등록중입니다.");
    }
  });
  //--상세보기 클릭되었을 때 할일 END--
});
