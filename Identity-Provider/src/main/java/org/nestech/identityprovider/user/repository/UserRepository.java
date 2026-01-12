package org.nestech.identityprovider.user.repository;

import org.nestech.identityprovider.user.model.Role;
import org.nestech.identityprovider.user.model.User;
import org.nestech.identityprovider.user.responses.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>,
        JpaSpecificationExecutor<User>, PagingAndSortingRepository<User, Integer> {
    @Transactional
    @Modifying
    @Query("update User u set u.password = ?1 where u.id = ?2")
    int updatePasswordById(String password, Integer id);
    /*@Transactional
    @Modifying
    @Query("update User u set u.employeeId = ?1 where u.id = ?2")
    int updateEmployeeIdById(@NonNull Integer employeeId, @NonNull Integer id);*/
    Optional<User> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update User u set u.locked = ?1 where u.id = ?2")
    void setLocked(@NonNull boolean isLocked, @NonNull Integer id);

    @Override
    Optional<User> findById(Integer integer);

    Page<User> findByLocked(boolean locked, Pageable pageable);

    Page<User> findAllByEmailContaining(String search, Pageable pageable);

}
