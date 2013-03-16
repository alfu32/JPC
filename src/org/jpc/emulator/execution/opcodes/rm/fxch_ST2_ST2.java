package org.jpc.emulator.execution.opcodes.rm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import org.jpc.emulator.processor.fpu64.*;
import static org.jpc.emulator.processor.Processor.*;

public class fxch_ST2_ST2 extends Executable
{

    public fxch_ST2_ST2(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
    }


    public fxch_ST2_ST2(int blockStart, int eip, int prefices, PeekableInputStream input)
    {
        super(blockStart, eip);
    }

    public Branch execute(Processor cpu)
    {
        double tmp = cpu.fpu.ST(2);
        cpu.fpu.setST(2, cpu.fpu.ST(2));
        cpu.fpu.setST(2, tmp);
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