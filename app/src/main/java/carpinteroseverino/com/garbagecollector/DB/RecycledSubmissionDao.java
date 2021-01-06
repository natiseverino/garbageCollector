package carpinteroseverino.com.garbagecollector.DB;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import carpinteroseverino.com.garbagecollector.RecycledSubmission;

@Dao
public interface RecycledSubmissionDao {

    @Insert
    void insert(RecycledSubmission recycled);

    @Query("SELECT * FROM 'recycled-submission' WHERE username LIKE :username")
    List<RecycledSubmission> getByUsername(String username);

    @Update
    void updateRecycledSubmission(RecycledSubmission recycled);


}
