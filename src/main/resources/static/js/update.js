// (1) 회원정보 수정
function update(userid) {
    console.log("userid: " + userid);

    let data = $("#profileUpdate").serialize();
    console.log("data: " + data)

    $.ajax({
        type: "put",
        url: `/api/user/${userid}`,
        data: data,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType: "json"
    }).done(res=>{
        console.log("update 성공: ")
        location.href=`/user/${userid}`;
    }).fail(error=>{
        console.log("update 실패: ")
    });

}