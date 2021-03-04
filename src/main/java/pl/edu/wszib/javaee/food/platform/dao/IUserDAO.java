package pl.edu.wszib.javaee.food.platform.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wszib.javaee.food.platform.model.AppUser;

import java.util.List;
import java.util.Optional;

/**
 * Created by Yevhenii Shevchenko at 2/12/21
 * Project name: food.platform
 **/
public interface IUserDAO extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findAppUserByName(String name);

    List<AppUser> findAppUserByRole(AppUser.Role role);
}
