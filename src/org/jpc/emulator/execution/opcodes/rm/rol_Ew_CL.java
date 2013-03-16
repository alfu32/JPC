package org.jpc.emulator.execution.opcodes.rm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import org.jpc.emulator.processor.fpu64.*;
import static org.jpc.emulator.processor.Processor.*;

public class rol_Ew_CL extends Executable
{
    final int op1Index;

    public rol_Ew_CL(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
        op1Index = Processor.getRegIndex(parent.operand[0].toString());
    }


    public rol_Ew_CL(int blockStart, int eip, int prefices, PeekableInputStream input)
    {
        super(blockStart, eip);
        op1Index = FastDecoder.Ew(modrm);
    }

    public Branch execute(Processor cpu)
    {
        Reg op1 = cpu.regs[op1Index];
            int shift = cpu.r_cl.get8() & (16-1);
            int reg0 = 0xFFFF&op1.get16();
            int res = (reg0 << shift) | (reg0 >>> (16 - shift));
            op1.set16((short)res);
            boolean bit0  = (res & 1 ) != 0;
            boolean bit31 = (res & (1 << (16-1))) != 0;
            if (cpu.r_cl.get8() > 0)
            {
                cpu.cf = bit0;
                if (cpu.r_cl.get8() == 1)
                {
                    cpu.of = bit0 ^ bit31;
                    cpu.flagStatus &= NOFCF;
                }
                else
                    cpu.flagStatus &= NCF;
            }
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