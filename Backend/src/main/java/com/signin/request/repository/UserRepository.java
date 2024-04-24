package com.signin.request.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.signin.request.entity.User;
import com.signin.request.payload.response.DashboardResponseModal;
import com.signin.request.payload.response.JustTestJoinQueryModal;
import com.signin.request.payload.response.JustTestNonRelationQuery;
import com.signin.request.payload.response.JustTestQueryModal;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByGmail(String gmail);

	// public static final String findAllByRole="SELECT u.name,u.gmail,v.role FROM
	// tmuser u JOIN tmauthority v ON u.userid=v.roleid WHERE v.role=?1";

	/**
	 * @Query("SELECT new
	 * com.signin.request.payload.response.DashboardResponseModal" +
	 * "(u.gmail,u.name,u.phoneNo,u.companyName,u.createdOn,u.updateOn,new
	 * com.signin.request.payload.response.StatusModal(u.status.status)) FROM User
	 * u") JOIN Status v ON u.userId=v.statusId List<DashboardResponseModal>
	 * getAllUserDetails();
	 * 
	 * === In this we are returning only necessary fields in the User Modal and
	 * status Modal
	 */

	@Query(value = "SELECT new com.signin.request.payload.response.DashboardResponseModal"
			+ "(u.gmail,u.name,u.phoneNo,u.companyName,u.createdOn,u.updateOn,v.status) FROM User u JOIN Status v ON u.userId=v.statusId", nativeQuery = false)
	List<DashboardResponseModal> getAllUserDetails();

	@Query(value = "SELECT u FROM User u JOIN Status v ON u.userId=v.statusId WHERE v.status=?1", nativeQuery = false)
	List<User> findUserByStatus(String status);

//	@Query(value="SELECT new com.signin.request.payload.response.JustTestQueryModal(af.gmail,new com.signin.request.payload.response.JustTestStatusQueryModal(af.status.status,af.status.statusMessage),(SELECT u.gmail FROM AuthUser u WHERE u.authority='ADMIN') AS authuser) FROM User af",nativeQuery = false)
//	List<JustTestQueryModal> queryTestListModal();

	@Query(value = "SELECT new com.signin.request.payload.response.JustTestQueryModal(af.gmail,new com.signin.request.payload.response.JustTestStatusQueryModal(af.status.status,af.status.statusMessage),(SELECT u.gmail FROM AuthUser u WHERE u.authority='ADMIN') AS authuser) FROM User af", nativeQuery = false)
	List<JustTestQueryModal> queryTestModal();

	@Query("SELECT new com.signin.request.payload.response.JustTestJoinQueryModal"
			+ "(u.gmail,v.status,v.statusMessage,(SELECT ps.gmail FROM AuthUser ps WHERE ps.authority='ADMIN') AS authuser) FROM User u JOIN Status v ON u.userId=v.statusId")
	List<JustTestJoinQueryModal> questTestJoinModal();

	@Query("SELECT new com.signin.request.payload.response.JustTestNonRelationQuery(u.gmail,u.name,u.phoneNo,u.companyName,v.authority) FROM User u JOIN AuthUser v ON u.userId=v.id")
	List<JustTestNonRelationQuery> queryTestNonRelationJoin();

}
