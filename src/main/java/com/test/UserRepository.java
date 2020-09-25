package com.test;

import com.couchbase.client.java.query.QueryScanConsistency;
import org.springframework.data.couchbase.repository.ScanConsistency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    Optional<User> findByEmailLike(String emailPart);
}