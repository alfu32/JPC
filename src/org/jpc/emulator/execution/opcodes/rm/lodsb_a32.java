package org.jpc.emulator.execution.opcodes.rm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import org.jpc.emulator.processor.fpu64.*;
import static org.jpc.emulator.processor.Processor.*;

public class lodsb_a32 extends Executable
{
    final int segIndex;

    public lodsb_a32(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
        segIndex = Processor.getSegmentIndex(parent.getSegment());
    }


    public lodsb_a32(int blockStart, int eip, int prefices, PeekableInputStream input)
    {
        super(blockStart, eip);
        segIndex = Processor.getSegmentIndex(parent.getSegment());
    }

    public Branch execute(Processor cpu)
    {
        Segment seg = cpu.segs[segIndex];
        StaticOpcodes.lodsb_a32(cpu, seg);
        return Branch.None;
    }

    public boolean isBranch()
    {
        return false;
    }

    public String toString()
    {
        return this.getClass().getName();
    }
}