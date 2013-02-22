package org.jpc.emulator.execution.opcodes.vm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import org.jpc.emulator.processor.fpu64.*;
import static org.jpc.emulator.processor.Processor.*;

public class fisttp_Mw_mem extends Executable
{
    final Pointer op1;

    public fisttp_Mw_mem(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
        op1 = new Pointer(parent.operand[0], parent.adr_mode);
    }

    public Branch execute(Processor cpu)
    {
        double cast = cpu.fpu.ST(0);
        if (Math.abs(cast) > Short.MAX_VALUE)
            cast = (double)Short.MIN_VALUE;
        op1.set16(cpu, (short)(Math.signum(cast)*Math.floor(Math.abs(cast))));
        cpu.fpu.pop();
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