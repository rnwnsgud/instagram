package snsProject.photogram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import snsProject.photogram.domain.Subscribe;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {

    @Modifying
    @Query(value = "INSERT INTO subscribe(fromUserId, toUserId, createDate) VALUES(:fromUserId, :toUserId, now())", nativeQuery = true)
    void subscribe(@Param("fromUserId") int fromUserId, @Param("toUserId") int toUserId);

    @Modifying
    @Query(value = "DELETE FROM subscribe WHERE fromUserId = :fromUserId AND toUserId = :toUserId", nativeQuery = true)
    void unSubscribe(@Param("fromUserId") int fromUserId, @Param("toUserId") int toUserId);
}
