function board_check(){
	
	if($("#title").val()==""){
		alert("작성자를 입력하세요.");
		$("#board_name").focus();
		return false;
	}
	if($("#contnet").val()==""){
		alert("제목을 입력하세요.");
		$("#board_title").focus();
		return false;
	}			
}

let index={
		init:function(){
			$("#btn-save").on("click",()=>{ // function(){},()=>{} this 를 바인딩하기 위해서
				this.save();
			}); // btn-save 클릭시 아래가 실행 된다. 
					
		},
		
    save:function(){
    	//alert("user의 save함수 호출됨");
    	let data={
    			title:$("#title").val(),
    			content:$("#content").val()
    			
    	};
    	
    	//console.log(data);   	
    	//회원가입
    	// ajax 호출시 default 가 비동기 호출
    	// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여  insert 요청한다. 
    	// ajax 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트 변환 가능하다.
    	$.ajax({
    		 type:"POST",
    		 url:"/api/board",// 컨트롤 주소 값
    		 data:JSON.stringify(data), //http body 데이터
    		 contentType:"application/json;charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
    		 dataType:"json"// 요청을 서버로 해서 응답이 왔을때 기본으로 모든적이 문자열(생긴것이 json)이라며=>javascript 오브젝트로변경
    		// .을 통해서 사용 => 글작성 수행
    	}).done(function(resp){
    		alert("글작성이 완료 되었습니다.");    		
    		location.href="/";  
    		
    	}).fail(function(error){ // 에러 
            alert(JSON.stringify(error));    		
    	}); 
    },
    
}

index.init(); // init 함수 호출 