package carpinteroseverino.com.garbagecollector.DB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import carpinteroseverino.com.garbagecollector.Recycled;
import carpinteroseverino.com.garbagecollector.RecycledSubmission;
import carpinteroseverino.com.garbagecollector.User;

@Database(entities = {User.class, Recycled.class, RecycledSubmission.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract RecycledDao recycledDao();
    public abstract RecycledSubmissionDao recycledSubmissionDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "carpinteroseverino.com.garbagecollector")
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}