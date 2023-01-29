package winterproject.market;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import winterproject.market.Service.MemberService;

@SpringBootApplication
public class MarketApplication {
	public static void main(String[] args) {

		SpringApplication.run(MarketApplication.class, args);
	}

}
