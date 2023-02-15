$(() => {
  // -- 가입하기 버튼 클릭되었을 때 할 일 start --

  // if ($id == '' || $pwd == '') {
  //   alert('아이디 or 비밀번호를 입력하세요.');
  //   $('#login').disabled = true;
  // } else {
  //   $('#login').disabled = false;
  // }

  $("#login").on("click", function () {
    let $id = $("#id").val();
    let $pwd = $("#pwd").val();
    let url = backUrl + "/member/login.do";

    $.ajax({
      xhrFields: {
        withCredentials: true,
      },
      url: url,
      method: "post",
      data: {
        id: $id,
        pwd: $pwd,
      },
      success: function (jsonObj) {
        console.log(jsonObj);
        if (jsonObj != null) {
          alert(jsonObj);
        } else {
          location.href = frontUrl + "/index.html";
        }
      },
      error: function (xhr) {
        alert("오류" + xhr.status);
      },
    });
  });
  // -- 가입하기 버튼 클릭되었을 때 할 일 end --
});
