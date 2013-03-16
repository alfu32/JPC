package org.jpc.emulator.execution.opcodes.vm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import org.jpc.emulator.processor.fpu64.*;
import static org.jpc.emulator.processor.Processor.*;

public class bswap_o32_rDIr15 extends Executable
{

    public bswap_o32_rDIr15(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
    }


    public bswap_o32_rDIr15(int blockStart, int eip, int prefices, PeekableInputStream input)
    {
        super(blockStart, eip);
    }

    public Branch execute(Processor cpu)
    {
        cpu.r_edi.set32(Integer.reverseBytes(cpu.r_edi.get32()));
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