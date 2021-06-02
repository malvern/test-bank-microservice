package zw.co.malvern.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.malvern.domain.Account;

public interface AccountRepository extends JpaRepository<Account,String> {
}
