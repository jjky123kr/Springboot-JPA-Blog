
let index={
		init:function(){
			$("#btn-save").on("click",()=>{ // function(){},()=>{} this 를 바인딩하기
											// 위해서
				this.save();
			}); // btn-save 클릭시 아래가 실행 된다.
			$("#btn-delete").on("click",()=>{ // function(){},()=>{} this 를
												// 바인딩하기 위해서
				this.deleteByid();
			}); // btn-delete 클릭시 아래가 실행 된다.
			$("#btn-update").on("click",()=>{ // function(){},()=>{} this 를
												// 바인딩하기 위해서
				this.update();
			}); // btn-delete 클릭시 아래가 실행 된다
			
		},
		
	// 글작성
    save:function(){
    	// alert("user의 save함수 호출됨");
    	let data={
    			title:$("#title").val(),
    			content:$("#content").val() 			
    	};
    
    	$.ajax({
    		 type:"POST",
    		 url:"/api/board",// 컨트롤 주소 값
    		 data:JSON.stringify(data), // http body 데이터
    		 contentType:"application/json;charset=utf-8", // body 데이터가 어떤
															// 타입인지(MIME)
    		 dataType:"json"// 요청을 서버로 해서 응답이 왔을때 기본으로 모든적이 문자열(생긴것이
							// json)이라며=>javascript 오브젝트로변경
    		// .을 통해서 사용 => 글작성 수행
    	}).done(function(resp){
    		alert("글작성이 완료 되었습니다.");    		
    		location.href="/";  
    		
    	}).fail(function(error){ // 에러
            alert(JSON.stringify(error));    		
    	}); 
    },
    
    // 글 삭제
    deleteByid:function(){	  
    	let id= $("#id").text();// 글작성 id 텍스트
    	
    	$.ajax({
    		 type:"DELETE",
    		 url:"/api/board/"+id,// 컨트롤 주소 값 , id값을 넘겨준다.
    		 dataType:"json"// 요청을 서버로 해서 응답이 왔을때 기본으로 모든적이 문자열(생긴것이
							// json)이라며=>javascript 오브젝트로변경
    	}).done(function(resp){
    		alert("글삭제가 완료 되었습니다.");    		
    		location.href="/";     		
    	}).fail(function(error){ // 에러
            alert(JSON.stringify(error));    		
    	}); 
    },
    
    //수정 
    update:function(){
    	// alert("user의 save함수 호출됨");
    	let id =$("#id").val();
    	
    	let data={
    			title:$("#title").val(),
    			content:$("#content").val()
    			
    	};
    
    	$.ajax({
    		 type:"PUT",
    		 url:"/api/board/"+id, // 컨트롤 주소 값 id값을 가져간다.
    		 data:JSON.stringify(data), // http body 데이터
    		 contentType:"application/json;charset=utf-8", // body 데이터가 어떤
															// 타입인지(MIME)
    		 dataType:"json"// 요청을 서버로 해서 응답이 왔을때 기본으로 모든적이 문자열(생긴것이
							// json)이라며=>javascript 오브젝트로변경
    		// .을 통해서 사용 => 글작성 수행
    	}).done(function(resp){
    		alert("글수정이 완료 되었습니다.");    		
    		location.href="/";  
    		
    	}).fail(function(error){ // 에러
            alert(JSON.stringify(error));    		
    	}); 
    },
    
}

index.init(); // init 함수 호출
