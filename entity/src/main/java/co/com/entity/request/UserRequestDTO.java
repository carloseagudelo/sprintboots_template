package co.com.entity.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

	private String id;
	private String name;
	private String email;
	private String password;
	private String role;
	
}
