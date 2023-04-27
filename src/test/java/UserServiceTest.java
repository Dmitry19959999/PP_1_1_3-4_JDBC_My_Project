import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class UserServiceTest {
    private final static UserService USER_SERVICE = new UserServiceImpl();

    private static final String TEST_NAME = "Ivan";
    private static final String TEST_LAST_NAME = "Ivanov";
    private static final byte TEST_AGE = 5;


    @Test
    public void dropUsersTable() {
        try {
            USER_SERVICE.dropUsersTable();
            USER_SERVICE.dropUsersTable();
        } catch (Exception e) {
            Assert.fail("При тестировании удаления таблицы произошло исключение\n" + e);
        }
    }

    @Test
    public void createUsersTable() {
        try {
            USER_SERVICE.dropUsersTable();
            USER_SERVICE.createUsersTable();
        } catch (Exception e) {
            Assert.fail("При тестировании создания таблицы пользователей произошло исключение\n" + e.getMessage());
        }
    }

    @Test
    public void saveUser() {
        try {
            USER_SERVICE.dropUsersTable();
            USER_SERVICE.createUsersTable();
            USER_SERVICE.saveUser(TEST_NAME, TEST_LAST_NAME, TEST_AGE);

            User user = USER_SERVICE.getAllUsers().get(0);

            if (!TEST_NAME.equals(user.getName())
                    || !TEST_LAST_NAME.equals(user.getLastName())
                    || TEST_AGE != user.getAge()
            ) {
                Assert.fail("User был некорректно добавлен в базу данных");
            }

        } catch (Exception e) {
            Assert.fail("Во время тестирования сохранения пользователя произошло исключение\n" + e);
        }
    }

    @Test
    public void removeUserById() {
        try {
            USER_SERVICE.dropUsersTable();
            USER_SERVICE.createUsersTable();
            USER_SERVICE.saveUser(TEST_NAME, TEST_LAST_NAME, TEST_AGE);
            USER_SERVICE.removeUserById(1L);
        } catch (Exception e) {
            Assert.fail("При тестировании удаления пользователя по id произошло исключение\n" + e);
        }
    }

    @Test
    public void getAllUsers() {
        try {
            USER_SERVICE.dropUsersTable();
            USER_SERVICE.createUsersTable();
            USER_SERVICE.saveUser(TEST_NAME, TEST_LAST_NAME, TEST_AGE);
            List<User> userList = USER_SERVICE.getAllUsers();

            if (userList.size() != 1) {
                Assert.fail("Проверьте корректность работы метода сохранения пользователя/удаления или создания таблицы");
            }
        } catch (Exception e) {
            Assert.fail("При попытке достать всех пользователей из базы данных произошло исключение\n" + e);
        }
    }

    @Test
    public void cleanUsersTable() {
        try {
            USER_SERVICE.dropUsersTable();
            USER_SERVICE.createUsersTable();
            USER_SERVICE.saveUser(TEST_NAME, TEST_LAST_NAME, TEST_AGE);
            USER_SERVICE.cleanUsersTable();

            if (USER_SERVICE.getAllUsers().size() != 0) {
                Assert.fail("Метод очищения таблицы пользователей реализован не корректно");
            }
        } catch (Exception e) {
            Assert.fail("При тестировании очистки таблицы пользователей произошло исключение\n" + e);
        }
    }

}
