package com.example.mail_project.repository;

import com.example.mail_project.entity.MasterDataDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MasterDataDetailRepository extends JpaSpecificationExecutor<MasterDataDetail>,
        JpaRepository<MasterDataDetail, Long>,
        PagingAndSortingRepository<MasterDataDetail, Long> {

    /*
 SELECT MASTER_DATA_DETAIL.VARIABLE1
FROM MASTER_DATA  LEFT JOIN MASTER_DATA_DETAIL
ON MASTER_DATA .ID  = MASTER_DATA_DETAIL .MASTERDATA
  */
//    @Query("SELECT a.variable1 FROM MasterData b LEFT JOIN MasterDataDetail a ON b.id  = a.masterdata where lower(a.variable1)like lower(concat('%',concat(:variable1,'%')) )")
//    List<MasterDataDetail> findByMasterDataDetai(@Param("variable1") String variable1);
     /*
SELECT MASTER_DATA .ID,MASTER_DATA.CODE,MASTER_DATA_DETAIL.CODE , MASTER_DATA_DETAIL.VARIABLE1
FROM MASTER_DATA
LEFT JOIN MASTER_DATA_DETAIL
ON MASTER_DATA .ID  = MASTER_DATA_DETAIL .MASTERDATA
where MASTER_DATA .ID = '100' AND MASTER_DATA_DETAIL.CODE LIKE 'progran.list'
String[] parts = string.split("-");
ค้นหา ดีเทล จาก data
  */
    @Query("SELECT DISTINCT b.id,b.code,a.code,a.variable1 FROM MasterData b LEFT JOIN MasterDataDetail a ON b.id  = a.masterdata where b.id = :id and a.code like concat(:code,'%') ")
    List<MasterDataDetail> findMasterDataDetailsByIdEquals(@Param("id") Long id, @Param("code") String code);

}
