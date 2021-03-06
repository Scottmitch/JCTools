package org.jctools.queues;

import org.jctools.queues.spec.ConcurrentQueueSpec;
import org.jctools.queues.spec.Ordering;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;

@RunWith(Parameterized.class)
public class MpqSanityTestSpscChunked extends MpqSanityTest
{
    @Parameterized.Parameters
    public static Collection<Object[]> parameters()
    {
        ArrayList<Object[]> list = new ArrayList<Object[]>();
        list.add(makeMpq(0, 1, 16, Ordering.FIFO, new SpscChunkedArrayQueue<>(8, 16)));// MPSC size 1
        list.add(makeMpq(0, 1, SIZE, Ordering.FIFO, new SpscChunkedArrayQueue<>(8, SIZE)));// MPSC size SIZE
        return list;
    }

    public MpqSanityTestSpscChunked(ConcurrentQueueSpec spec, MessagePassingQueue<Integer> queue)
    {
        super(spec, queue);
    }

    @Test
    public void testMaxSizeQueue()
    {
        SpscChunkedArrayQueue queue = new SpscChunkedArrayQueue<Object>(1024, 1000 * 1024 * 1024);
        for (int i = 0; i < 400001; i++)
        {
            queue.offer(i);
        }
    }
}
