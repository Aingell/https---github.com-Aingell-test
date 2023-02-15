$(() => {
  // 아이디 중복 체크
  $(".id").on("input", function () {
    let url = backURL + "member/idcheck.do";
    let userid = $(".id").val();
    if (userid == "") {
      $("#id_input_error").html(" ");
      return;
    }
    $.ajax({
      url: url,
      method: "post",
      data: {
        id: userid,
      },
      success: function (jsonObj) {
        if (jsonObj.status == 1) {
          $("#id_input_error").html("사용 가능한 아이디");
          $("#id_input_error").css("color", "#008000");
        } else {
          $("#id_input_error").html("중복된 아이디");
          $("#id_input_error").css("color", "#f15746");
        }
      },
      error: function (xhr) {
        alert("오류" + xhr.status);
      },
    });
  });

  // 위에서 입력한 비밀번호 일치 여부 확인
  $("#pwd").on("input", function () {
    let prePwd = $("#prepwd").val();
    let checkPwd = $("#pwd").val();
    let errorMsg = "";
    if (prePwd == "" || checkPwd == "") {
      $("#pwd_input_error").html(errorMsg);
    } else {
      if (prePwd === checkPwd) {
        errorMsg = "이전 비밀번호와 일치함";
        $("#pwd_input_error").html(errorMsg);
        $("#pwd_input_error").css("color", "#008000");
      } else {
        errorMsg = "이전 비밀번호와 일치하지 않음";
        $("#pwd_input_error").html(errorMsg);
        $("#pwd_input_error").css("color", "#f15746");
      }
    }
  });

  //디바운스
  let timer = false; //최초 false
  const filterByDebounce = (e, callback) => {
    if (timer) {
      clearTimeout(timer);
    }
    timer = setTimeout(function () {
      callback("" + e.target.value);
    }, 200); //200ms 이후 반응(디바운스)
  };

  //이메일 유효성검사
  function validateEmail(strEmail) {
    const reg_email =
      /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
    if (!reg_email.test("" + strEmail)) {
      return false;
    }
    return true;
  }

  //비밀번호 유효성 검사
  function validatePassword(strPassword) {
    const reg_password =
      /^.*(?=^.{8,16}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
    if (!reg_password.test("" + strPassword)) {
      return false;
    }
    return true;
  }

  //휴대폰번호 유효성 검사
  function validateTell(strTell) {
    const reg_tell = /^01(?:0|1|[6-9])(?:\d{3}|\d{4})\d{4}$/;
    if (!reg_tell.test("" + strTell)) {
      return false;
    }
    return true;
  }

  // 이름 유효성검사
  function validateName(strName) {
    const reg_name = /^[가-힣a-zA-Z]+$/;
    if (!reg_name.test("" + strName)) {
      return false;
    }
    return true;
  }

  // 이메일 유효성 검사
  document.querySelector(".email").addEventListener("input", (e) => {
    filterByDebounce(e, (strEmail) => {
      let errorMsg = "";
      if (!validateEmail(strEmail)) {
        errorMsg = "이메일 주소를 정확히 입력해주세요.";
      } else {
      }
      document.querySelector("#email_input_error").innerHTML = errorMsg;
    });
  });

  // 이전 비밀번호 유효성 검사
  document.querySelector("#prepwd").addEventListener("input", (e) => {
    filterByDebounce(e, (strPassword) => {
      let errorMsg = "";
      if (!validatePassword(strPassword)) {
        errorMsg = "영문, 숫자, 특수문자를 조합해서 입력해주세요. (8-16자)";
      } else {
      }
      document.querySelector("#password_input_error").innerHTML = errorMsg;
    });
  });

  // 변경 비밀번호 유효성 검사
  document.querySelector("#pwd_input_error").addEventListener("input", (e) => {
    filterByDebounce(e, (strPassword) => {
      let errorMsg = "";
      if (!validatePassword(strPassword)) {
        errorMsg = "영문, 숫자, 특수문자를 조합해서 입력해주세요. (8-16자)";
      } else {
      }
      document.querySelector("#pwd_input_error").innerHTML = errorMsg;
    });
  });

  // 이름 유효성 검사
  document.querySelector("#name_input").addEventListener("input", (e) => {
    filterByDebounce(e, (strName) => {
      let errorMsg = "";
      if (!validateName(strName)) {
        errorMsg = "올바른 이름을 입력해주세요. (2 - 50자)";
      } else {
      }
      document.querySelector("#name_input_error").innerHTML = errorMsg;
    });
  });

  // 전화번호 유효성 검사
  document.querySelector("#hp_input").addEventListener("input", (e) => {
    filterByDebounce(e, (strTell) => {
      let errorMsg = "";
      if (!validateTell(strTell)) {
        errorMsg = "휴대폰 번호를 정확히 입력해주세요.('-'제외 후 입력)";
      } else {
      }
      document.querySelector("#tel_input_error").innerHTML = errorMsg;
    });
  });

  // -- 폼 서브밋되었을 때 할 일 start --
  $(".joinForm").submit(() => {
    let url = backURL + "member/add.do";
    let $id = $(".id").val();
    let $pwd = $("#pwd").val();
    let $name = $(".name").val();
    let $email = $(".email").val();
    let $birth = $(".birth").val();
    let $cellphoneNo = $(".cellphoneNo").val();
    $.ajax({
      url: url,
      method: "post",
      data: {
        id: $id,
        pwd: $pwd,
        mName: $name,
        email: $email,
        birth: $birth,
        tel: $cellphoneNo,
      },
      success: function () {
        location.href = frontUrl + "index.html";
      },
      error: function (xhr) {
        alert("오류" + xhr.status);
      },
    });
    return false;
  });
  // -- 폼 서브밋되었을 때 할 일 end --
});
