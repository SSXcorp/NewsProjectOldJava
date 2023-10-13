package ssx.info.web.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ssx.info.web.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select * from users where email =?1", nativeQuery = true)
    User getUserByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.first_name = :first_name")
    public User findByFirstName(@Param("first_name")String firstName);
}