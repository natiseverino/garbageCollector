package carpinteroseverino.com.garbagecollector.DB;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import carpinteroseverino.com.garbagecollector.Recycled;

@Dao
public interface RecycledDao {

    @Insert
    void insert(Recycled recycled);

    @Query("SELECT * FROM recycled WHERE username LIKE :username")
    Recycled getByUsername(String username);

    @Update
    void updateRecycled(Recycled recycled);
}
