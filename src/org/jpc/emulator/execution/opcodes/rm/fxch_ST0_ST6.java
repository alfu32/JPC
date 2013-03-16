package org.jpc.emulator.execution.opcodes.rm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import org.jpc.emulator.processor.fpu64.*;
import static org.jpc.emulator.processor.Processor.*;

public class fxch_ST0_ST6 extends Executable
{

    public fxch_ST0_ST6(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
    }


    public fxch_ST0_ST6(int blockStart, int eip, int prefices, PeekableInputStream input)
    {
        super(blockStart, eip);
    }

    public Branch execute(Processor cpu)
    {
        double tmp = cpu.fpu.ST(0);
        cpu.fpu.setST(0, cpu.fpu.ST(6));
        cpu.fpu.setST(6, tmp);
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