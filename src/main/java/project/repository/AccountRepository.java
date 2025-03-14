package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByUserId(Long currentUserId);
}
