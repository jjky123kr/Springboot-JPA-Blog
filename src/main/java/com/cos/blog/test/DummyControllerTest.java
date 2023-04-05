package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

//html 파일이 아니라 data를 리턴해주는 controller => @RestController
@RestController
public class DummyControllerTest {

	private static final String sort = null;
	@Autowired //의존성 주임
	private UserRepository userRepository;
	
	 // 삭제 
    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id) {
    	try {
    	userRepository.deleteById(id);
    	}catch (EmptyResultDataAccessException e) {  //  Exception 가능하다. 
			return"삭제에 실패하였습니다. 해당 id는 데이터 베이스에 없습니다.";
		}  	
    	return"삭제되었습니다.id:"+id;
    }
    
	
	@Transactional // 함수 종료시에  자동으로 commit 됨. 
	    @PutMapping("/dummy/user/{id}")   // json 경우는 requestBody 사용
	    public User updateUser(@PathVariable int id,@RequestBody User requestUser) { //json데이터를 요청=>JavaO()ject(MessageConverter의 Jackson라이브러리가 변환해서 받아준다.
	   	 System.out.println("id:"+id);
	   	 System.out.println("password:"+requestUser.getPassword()); //5678
	   	 System.out.println("email:"+requestUser.getEmail());             // love@gamil.com
	      
	   	  User user = userRepository.findById(id).orElseThrow(()->{ // 람다식 
	   		 return new  IllegalArgumentException("수정에 실패했습니다.");
	   	  });
	   	  user.setPassword(requestUser.getPassword());
	   	  user.setEmail(requestUser.getEmail());
	   	    
	   	 //requestUser.setId(id);
	   	// requestUser.setUsername("test");  
	   	  
	   	 //UserRepository.save(user);  
	   	  // 더티 체킹
	   	 return user;
	    }
	
	//http://loclhost:8000/blog/dummy/user
	@GetMapping("dummy/users")
	public List<User>list(){
		return userRepository.findAll(); // 전체를 리턴 
	}
	 // 한 페이지당 2건의 데이터 출력 
    // Sort.Direction.DESC = 최신순서 
    // http://localhost/blog/dummy/user?page=1
    @GetMapping("dummy/user/page")
    public Page<User>pageList(@PageableDefault(size=2,sort="id",direction=org.springframework.data.domain.Sort.Direction.DESC)Pageable pageable){
   	Page<User>pageinUser=userRepository.findAll(pageable);
   	
   	List<User>users=pageinUser.getContent(); //List 로 받아서 리턴하는 것이 좋다.
   	return  pageinUser;
    }
    
	
	//{id}주소로 파라레터를 전달
	 //http://loclhost:8000/blog/dummy/user/1
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
	      
		// findById 가 optional인 이유
		//user/4을 찾으면 내가 데이터베이스에서 못찾아오게되면, user가 null값이 될텐데
		// 그럼 return 값으로 null이 리턴되어서, 그럼 프로그램에 문제가 생겨서 ,
		// 그래서 optional로 너의 user객체를 감싸서 가져올테니 null이 아닌지 판단해서 return해
		
		// 람다식
//		 User user=UserRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("해당유저는 없습니다.id:"+id); IllegalArgumentException
//			});
		
		// orElseThrow를 사용하여 코드 가독성 향상!!
	    // 해당 item이 없다면 예외, 있다면 return -> 가독성 향상
	 User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
		    // UserRepository.findById(id).get();으로 하면, null 없으니 return 해라<문제가 없으니>
		 // DB에 해당하는 id 값을 select 할때 user값이 들어오는데 , 	
		 // 해당하지 하지 않는 id 값이 들어오면,.orElseGet()으로 들어오면서, User() 빈객체를 user에 넣어준다. 
		 // 이때 빈객체를 넣어주면 적어도 null이 아니게 된다.
		 // IllegalArgumentException 던져준다.
		 
		@Override
		public IllegalArgumentException get() {
			// TODO Auto-generated method stub
			return new IllegalArgumentException("해당 유저는 없습니다. ");
		}
	});
	 // 요청 : 웹브라우저 
	 //user 객체=> 자바 오브젝트 
	 // 변환(웹브라우저가 이해할수 있는 데이터 )-> json 사용한다. 
	 //스부링부트 =MessageConverter라는 애가 응답시에 자동 작동
	 // 만약  자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리 호출
	 // user 오브젝트를 json 으로 변환해서 브라우저에 던져준다. 
	 return user;
	}
	   
	
	//http://localhost:8000/blog/dummy/join(요청)
	//http의 body에 username password email 데이터를 가지고 요청
	@PostMapping("/dummy/join")
	public String join(User user) {
	   System.out.println("id:"+user.getId());
	   System.out.println("username:"+user.getUsername());
	   System.out.println("passwrod:"+user.getPassword());
	   System.out.println("email:"+user.getEmail());
	   System.out.println("role:"+user.getRole());
	   System.out.println("create:"+user.getCreateDate());
	   
	   user.setRole(RoleType.USER); 
	   userRepository.save(user);  
		return "회원가입이 완료되었습니다.";  
	}
}       
                    