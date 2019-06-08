package mars_rover;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


@RunWith(JUnitParamsRunner.class)
public class MarsRoverTest {

    private Rover rover;

    @Before
    public void initialise() {
        SpaceGrid spaceGrid = new SpaceGrid();
        rover = new Rover(spaceGrid);
    }

    @Test
    @Parameters({
            "init, Initial Coordinate is x=0 y=0 the Range is x=10 y=10"
    })
    public void be_able_to_show_initial_information(String commands, String info) {
        if (commands.contains("init")) assertThat(rover.showInit(commands), is(info));
    }

    @Test
    @Parameters({
            "R, 0:0:E", "RR, 0:0:S", "RRR, 0:0:W", "RRRR, 0:0:N"
    })
    public void be_able_to_rotate_right(String commands, String position) {
        test_commands_execute(commands, position);
    }

    @Test
    @Parameters({
            "L, 0:0:W", "LL, 0:0:S"
    })
    public void be_able_to_rotate_left(String commands, String position) {
        test_commands_execute(commands, position);
    }

    @Test
    @Parameters({
            "M, 0:1:N", "MMM, 0:3:N"
    })
    public void be_able_to_move_forward(String commands, String position) {
        test_commands_execute(commands, position);
    }

    @Test
    @Parameters({
            "MMMMMMMMMM, 0:0:N", "MMMMMMMMMMMMM, 0:3:N"
    })
    public void be_able_to_move_from_bottom_when_top_out(String commands, String position) {
        test_commands_execute(commands, position);
    }

    @Test
    @Parameters({
            "RM, 1:0:E", "RMMMMM, 5:0:E"
    })
    public void be_able_to_move_right(String commands, String position) {
        test_commands_execute(commands, position);
    }

    @Test
    @Parameters({
            "RMMMMMMMMMM, 0:0:E", "RMMMMMMMMMMM, 1:0:E"
    })
    public void be_able_to_move_from_left_when_right_out(String commands, String position) {
        test_commands_execute(commands, position);
    }

    @Test
    @Parameters({
            "LM, 9:0:W", "LMMMM, 6:0:W"
    })
    public void be_able_to_move_left(String commands, String position) {
        test_commands_execute(commands, position);
    }

    @Test
    @Parameters({
            "LLM, 0:9:S", "LLMMMM, 0:6:S"
    })
    public void be_able_to_move_south(String commands, String position) {
        test_commands_execute(commands, position);
    }

    @Test
    @Parameters({
            "BB, 0:8:N", "BBLB, 1:8:W", "MMB, 0:1:N", "BBRB,9:8:E"
    })
    public void be_able_to_free_back(String commands, String position) {
        test_commands_execute(commands, position);
    }

    @Test
    @Parameters({
            "MMMM, 0:3:N", "RMMMM, 1:0:E"
    })
    public void be_able_to_stop_when_obstacle(String commands, String position) {
        Coordinate obstacle_0_4 = new Coordinate(0, 4);
        Coordinate obstacle_2_0 = new Coordinate(2, 0);
        assertThat(new Rover(new SpaceGrid(asList(obstacle_0_4, obstacle_2_0))).execute(commands), is(position));
    }

    private void test_commands_execute(String commands, String position) {
        assertThat(rover.execute(commands), is(position));
    }
}
