package alexzandr.justtestapp.local.dao

import alexzandr.justtestapp.local.models.entities.ImageConfigEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
abstract class AppConfigDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertImageConfig(config: ImageConfigEntity): Completable

    @Query("SELECT * FROM image_config ")
    abstract fun getImageConfig(): Single<List<ImageConfigEntity>>

}