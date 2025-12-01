package de.seuhd.campuscoffee.tests.system;
import java.util.List;
import static de.seuhd.campuscoffee.tests.SystemTestUtils.Requests.userRequests;
import org.junit.Test;
import de.seuhd.campuscoffee.domain.model.User;
import de.seuhd.campuscoffee.domain.tests.TestFixtures;


public class UsersSystemTests extends AbstractSysTest {

    //DONE: Uncomment once user endpoint is implemented
    

    @Test
    void createUser() {
        User userToCreate = TestFixtures.getUserListForInsertion().getFirst();
        User createdUser = userDtoMapper.toDomain(userRequests.create(List.of(userDtoMapper.fromDomain(userToCreate))).getFirst());

        assertEqualsIgnoringIdAndTimestamps(createdUser, userToCreate);
    }

    //DONE: Add at least two additional tests for user operations
    @Test
    void getUserById(){
        User userToCreate= TestFixtures.getUserListForInsertion().getFirst();
        User createdUser = userDtoMapper.toDomain(userRequests.create(List.of(userDtoMapper.fromDomain(userToCreate))).getFirst());
        User fetchedUser= userDtoMapper.toDomain(userRequests.retrieveById(createdUser.id()));
        assertEqualsIgnoringIdAndTimestamps(fetchedUser, userToCreate);

    }
    @Test void retrieveUserByFilter(){
        User userToCreate= TestFixtures.getUserListForInsertion().getFirst();
        User createdUser = userDtoMapper.toDomain(userRequests.create(List.of(userDtoMapper.fromDomain(userToCreate))).getFirst());
        User fetchedUser= userDtoMapper.toDomain(userRequests.retrieveByFilter("loginName", createdUser.loginName()));
        assertEqualsIgnoringIdAndTimestamps(fetchedUser, userToCreate);
       
    }

       

    }
    

