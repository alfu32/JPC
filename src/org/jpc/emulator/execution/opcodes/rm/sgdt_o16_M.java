package org.jpc.emulator.execution.opcodes.rm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import org.jpc.emulator.processor.fpu64.*;
import static org.jpc.emulator.processor.Processor.*;

public class sgdt_o16_M extends Executable
{
    final Address op1;

    public sgdt_o16_M(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
        op1 = new Address();//won't work any more delete soon
    }


    public sgdt_o16_M(int blockStart, int eip, int prefices, PeekableInputStream input)
    {
        super(blockStart, eip);
        op1 = Modrm.getPointer(prefices, modrm, input);
    }

    public Branch execute(Processor cpu)
    {
        int addr = op1.get(cpu) + op1.getBase(cpu);
        cpu.linearMemory.setWord(addr, (short)cpu.gdtr.getLimit());
        cpu.linearMemory.setDoubleWord(addr+2, cpu.gdtr.getBase() & 0x00ffffff);
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