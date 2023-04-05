let index={
		init:function(){
			$("#btn-save").on("click",()=>{ // function(){},()=>{} this 를 바인딩하기
				   this.save();
			}); 
			
			$("#btn-update").on("click",()=>{ // function(){},()=>{} this 를
												// 바인딩하기
               this.update();
        }); // btn-save 클릭시 아래가 실행 된다.
						
		},
		
    save:function(){
    	// alert("user의 save함수 호출됨");
    	let data={
    			username:$("#username").val(),
    			password:$("#password").val(),
    			email:$("#email").val()
    	};
    	
    	// console.log(data);
    	// 회원가입
    	// ajax 호출시 default 가 비동기 호출
    	// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청한다.
    	// ajax 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트 변환 가능하다.
    	$.ajax({
    		 type:"POST",
    		 url:"/auth/joinProc" ,// 컨트롤 주소 값
    		 data:JSON.stringify(data), // http body 데이터
    		 contentType:"application/json;charset=utf-8", // body 데이터가 어떤
															// 타입인지(MIME)
    		 dataType:"json"// 요청을 서버로 해서 응답이 왔을때 기본으로 모든적이 문자열(생긴것이
							// json)이라며=>javascript 오브젝트로변경
    		// .을 통해서 사용 => 회원가입 수행 요청
    	}).done(function(resp){
    		alert("회원가입이 완료되었습니다.");    		
    		location.href="/"; 
    		
    	}).fail(function(error){ // 에러
            alert(JSON.stringify(error));    		
    	}); 
    },
    
// 기존 로그인 인증

// login:function(){
// //alert("user의 save함수 호출됨");
// let data={
// username:$("#username").val(),
// password:$("#password").val()
// };
//    	
// //console.log(data);
//    	
// // ajax 호출시 default 가 비동기 호출
// // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청한다.
// // ajax 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트 변환 가능하다.
// $.ajax({
// type:"POST",
// url:"/api/user/login" ,// 컨트롤 주소 값
// data:JSON.stringify(data), //http body 데이터
// contentType:"application/json;charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
// dataType:"json"// 요청을 서버로 해서 응답이 왔을때 기본으로 모든적이 문자열(생긴것이 json)이라며=>javascript
// 오브젝트로변경
// // .을 통해서 사용 => 회원가입 수행 요청
// }).done(function(resp){
// alert("로그인이 완료되었습니다.");
// location.href="/";
//    		
// }).fail(function(error){ // 에러
// alert(JSON.stringify(error));
// });
// }

    update:function(){
    	// alert("user의 save함수 호출됨");
    	let data={
    			id:$("#id").val(),
    			username:$("#username").val(),
    			password:$("#password").val(),
    			email:$("#email").val()
    	};
    	
    	$.ajax({
    		 type:"PUT",
    		 url:"/user" ,// 컨트롤 주소 값
    		 data:JSON.stringify(data), // http body 데이터
    		 contentType:"application/json;charset=utf-8", // body 데이터가 어떤
															// 타입인지(MIME)
    		 dataType:"json"// 요청을 서버로 해서 응답이 왔을때 기본으로 모든적이 문자열(생긴것이
							// json)이라며=>javascript 오브젝트로변경
    		// .을 통해서 사용 => 회원가입 수행 요청
    	}).done(function(resp){
    		alert("회원수정이 완료되었습니다.");    		
    		location.href="/"; 
    		
    	}).fail(function(error){ // 에러
            alert(JSON.stringify(error));    		
    	}); 
    },   
    
       
}

index.init(); // init 함수 호출
