package org.jpc.emulator.execution.opcodes.rm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import org.jpc.emulator.processor.fpu64.*;
import static org.jpc.emulator.processor.Processor.*;

public class lgdt_o32_M_mem extends Executable
{
    final Address op1;

    public lgdt_o32_M_mem(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
        op1 = new Address();//won't work any more delete soon
    }


    public lgdt_o32_M_mem(int blockStart, int eip, int prefices, PeekableInputStream input)
    {
        super(blockStart, eip);
        op1 = Modrm.getPointer(prefices, modrm, input);
    }

    public Branch execute(Processor cpu)
    {
        int addr = op1.get(cpu) + op1.getBase(cpu);
        int limit = 0xffff & cpu.linearMemory.getWord(addr);
        int base = cpu.linearMemory.getDoubleWord(addr+2);
        cpu.gdtr = cpu.createDescriptorTableSegment(base, limit);
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