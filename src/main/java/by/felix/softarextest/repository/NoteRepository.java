package by.felix.softarextest.repository;

import by.felix.softarextest.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("select n from Note n where n.userId = :userId")
    Optional<List<Note>> getAllUserNotes(@Param("userId") long userId);

    @Query("delete from Note n where n.userId = :userId")
    void deleteAllByUserId(@Param("userId") long userId);

}
