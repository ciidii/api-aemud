package com.amud.io.aemudapi.repositories;

import com.amud.io.aemudapi.entities.LogMemberBackup;
import com.amud.io.aemudapi.entities.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LogMemberBackupRepository extends CrudRepository<LogMemberBackup, Long> {


    @Query("FROM LogMemberBackup where isBackup is false")
    Optional<List<LogMemberBackup>> findAllWhereBackupIsFalse();

    @Query("select l from LogMemberBackup l where l.member= :member")
    List<LogMemberBackup> findAllByMemberId(@Param("member") Member member);
}
