package org.causwengteam13.issuetrackerserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class IssueTrackerServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IssueTrackerServerApplication.class, args);
	}

}
