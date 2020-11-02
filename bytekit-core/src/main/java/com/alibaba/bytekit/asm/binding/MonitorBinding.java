package com.alibaba.bytekit.asm.binding;

import com.alibaba.deps.org.objectweb.asm.Type;
import com.alibaba.deps.org.objectweb.asm.tree.InsnList;
import com.alibaba.deps.org.objectweb.asm.tree.LocalVariableNode;
import com.alibaba.bytekit.asm.location.Location;
import com.alibaba.bytekit.asm.location.Location.SyncEnterLocation;
import com.alibaba.bytekit.asm.location.Location.SyncExitLocation;
import com.alibaba.bytekit.utils.AsmOpUtils;

public class MonitorBinding extends Binding {

    @Override
    public void pushOntoStack(InsnList instructions, BindingContext bindingContext) {
        Location location = bindingContext.getLocation();

        if (location.isWhenComplete()) {
            throw new IllegalArgumentException("MonitorBinding only support location whenComplete is false.");
        }

        if (location instanceof SyncEnterLocation || location instanceof SyncExitLocation) {
            LocalVariableNode monitorVariableNode = bindingContext.getMethodProcessor().initMonitorVariableNode();
            AsmOpUtils.loadVar(instructions, AsmOpUtils.OBJECT_TYPE, monitorVariableNode.index);
        } else {
            throw new IllegalArgumentException(
                    "MonitorBinding only support SyncEnterLocation or SyncExitLocation. location: " + location);
        }
    }

    @Override
    public boolean fromStack() {
        return true;
    }

    @Override
    public Type getType(BindingContext bindingContext) {
        return AsmOpUtils.OBJECT_TYPE;
    }

}
