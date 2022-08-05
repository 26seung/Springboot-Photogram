// (1) 회원정보 수정
function update(userid,event) {
    console.log("userid: " + userid);

    event.preventDefault();     // 폼태그 액션을 막는것
    let data = $("#profileUpdate").serialize();
    console.log("data: " + data)

    $.ajax({
        type: "put",
        url: `/api/user/${userid}`,
        data: data,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType: "json"
    }).done(res=>{
        console.log("update 성공: ", res.data)
        location.href=`/user/${userid}`;
    }).fail(error=>{
        if(error.data == null){
            console.log("update 실패 & data 없음: " , error)
            alert(error.responseJSON.message);
        }else{
            console.log("update 실패: " , error.responseJSON.data)
            alert(error.responseJSON.data.name)
        }
    });

}