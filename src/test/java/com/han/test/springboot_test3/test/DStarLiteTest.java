package com.han.test.springboot_test3.test;

import com.han.test.springboot_test3.utils.dstarlite.DStarLite;
import com.han.test.springboot_test3.utils.dstarlite.State;
import org.junit.Test;

import java.util.List;

public class DStarLiteTest {
    @Test
    public void testMain(){
        DStarLite pf = new DStarLite();
        pf.init(0,1,5,2);
        pf.updateCell(2, 1, -1);
        pf.updateCell(2, 0, -1);
        pf.updateCell(2, 2, -1);
        pf.updateCell(2, 3, -1);
        pf.updateCell(2, 4, -1);
        pf.updateCell(2, 2, -1);
        pf.updateCell(3, 0, -1);

        System.out.println("Start node: (0,1)");
        System.out.println("End node: (5,2)");

        //Time the replanning
        long begin = System.currentTimeMillis();
        pf.replan();
//		pf.updateGoal(3, 2);
        long end = System.currentTimeMillis();

        System.out.println("Time: " + (end-begin) + "ms");

        List<State> path = pf.getPath();
        for (State i : path)
        {
            System.out.println("(x,y): (" + i.x + "," + i.y + ")");
        }

    }
}
