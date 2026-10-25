#!/usr/bin/env python3
"""Migrate the legacy interact handler to the split attack/interact NMS protocol."""
from pathlib import Path
p=Path('core/src/main/java/dev/by1337/entityrender/core/InteractionHooks.java')
s=p.read_text()
if 'ServerboundInteractPacket.Handler' not in s:
    raise SystemExit(0)
s=s.replace('import net.minecraft.network.protocol.game.ServerboundInteractPacket;', 'import net.minecraft.network.protocol.game.ServerboundInteractPacket;\nimport net.minecraft.network.protocol.game.ServerboundAttackPacket;')
a=s.index('                    if (!closed && message')
b=s.index('                    super.channelRead',a)
s=s[:a]+'''                    if (!closed && message instanceof ServerboundAttackPacket packet && owner.containsEntity(packet.entityId())) {
                        serverThread.execute(() -> {
                            if (!closed) owner.dispatchInteraction(player, packet.entityId(), EntityInteraction.Type.ATTACK, null, null, player.isSneaking());
                        });
                        return;
                    }
                    if (!closed && message instanceof ServerboundInteractPacket packet && owner.containsEntity(packet.entityId())) {
                        Vec3 hit = packet.location();
                        if (hit == null || !Double.isFinite(hit.x) || !Double.isFinite(hit.y) || !Double.isFinite(hit.z)) return;
                        EquipmentSlot slot = packet.hand() == InteractionHand.MAIN_HAND ? EquipmentSlot.HAND : EquipmentSlot.OFF_HAND;
                        Vector point = new Vector(hit.x, hit.y, hit.z);
                        serverThread.execute(() -> {
                            if (!closed) owner.dispatchInteraction(player, packet.entityId(), EntityInteraction.Type.INTERACT_AT, slot, point, packet.usingSecondaryAction());
                        });
                        return;
                    }
'''+s[b:]
p.write_text(s)
p=Path('core/src/test/java/dev/by1337/entityrender/core/InteractionChecks.java')
s=p.read_text().replace('import net.minecraft.network.protocol.game.ServerboundInteractPacket;', 'import net.minecraft.network.protocol.game.ServerboundInteractPacket;\nimport net.minecraft.network.protocol.game.ServerboundAttackPacket;').replace('            case "isOnline" -> true;', '            case "isOnline", "isSneaking" -> true;')
s=s.replace('ServerboundInteractPacket.createInteractionPacket(nativeTarget, true, InteractionHand.OFF_HAND, new Vec3(.25, .5, .75))','new ServerboundInteractPacket(nativeTarget.getId(), InteractionHand.OFF_HAND, new Vec3(.25, .5, .75), true)').replace('ServerboundInteractPacket.createAttackPacket(nativeTarget, false)','new ServerboundAttackPacket(nativeTarget.getId())')
s=s.replace('            entity.hide(viewer);','''            channel.writeInbound(new ServerboundAttackPacket(nativeTarget.getId()));
            while (!scheduled.isEmpty()) scheduled.removeFirst().run();
            require(callbacks.size() == 2 && callbacks.getLast().type() == EntityInteraction.Type.ATTACK && callbacks.getLast().secondaryAction(), "Attack packet lost its server-side sneaking state");
            entity.hide(viewer);''').replace('callbacks.size() == 1, "Non-viewer', 'callbacks.size() == 2, "Non-viewer')
p.write_text(s)
