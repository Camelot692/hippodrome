import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    public void constructor_Param_NullList_IllegalArgumentException_Throws(){
        List<Horse> horses = null;
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
        assertEquals("Horses cannot be null.", thrown.getMessage());
    }

    @Test
    public void constructor_Param_EmptyList_IllegalArgumentException_Throws(){
        List<Horse> horses = new ArrayList<>();
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
        assertEquals("Horses cannot be empty.", thrown.getMessage());
    }

    @Test
    void getHorses_receiveEqualListHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++){
            horses.add(new Horse("nameHorse" + i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertNotNull(hippodrome.getHorses());                          // !!!  from example
        assertEquals(30, hippodrome.getHorses().size());
        for (int j = 0; j < 30; j++){
            assertEquals("nameHorse" + j, hippodrome.getHorses().get(j).getName());
        }
    }

    @Test
    void move_callMoveAllHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++){
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (Horse h : horses){
            Mockito.verify(h, Mockito.times(1)).move();  //один раз викликаємо move для h
        }

    }

    @Test
    void getWinner() {
        Hippodrome hippodrome = new Hippodrome(List.of(
                new Horse("Horse1", 1, 11),
                new Horse("Horse2", 2, 22),
                new Horse("Horse3", 3, 33)
        ));

        assertEquals("Horse3", hippodrome.getWinner().getName());
    }
}