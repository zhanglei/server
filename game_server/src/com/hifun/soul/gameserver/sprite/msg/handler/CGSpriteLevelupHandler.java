package com.hifun.soul.gameserver.sprite.msg.handler;

import org.springframework.stereotype.Component;

import com.hifun.soul.core.msg.MessageType;
import com.hifun.soul.core.msg.injection.IMessageHandlerWithType;
import com.hifun.soul.gameserver.common.GameServerAssist;
import com.hifun.soul.gameserver.function.GameFuncType;
import com.hifun.soul.gameserver.human.Human;
import com.hifun.soul.gameserver.player.Player;
import com.hifun.soul.gameserver.sprite.msg.CGSpriteLevelup;

/**
 * 请求升级精灵;
 * 
 * @author crazyjohn
 * 
 */
@Component
public class CGSpriteLevelupHandler implements
		IMessageHandlerWithType<CGSpriteLevelup> {

	@Override
	public short getMessageType() {
		return MessageType.CG_SPRITE_LEVELUP;
	}

	@Override
	public void execute(CGSpriteLevelup message) {
		// 精灵升级
		Player player = message.getPlayer();
		if (player == null) {
			return;
		}
		Human human = player.getHuman();
		if (human == null) {
			return;
		}
		// 功能是否开启
		if (!GameServerAssist.getGameFuncService().gameFuncIsOpen(human,
				GameFuncType.SPRITE, true)) {
			return;
		}
		// 精灵升级
		human.getHumanSpriteManager().levelUpSprite(message.getSpriteUUID());
	}

}
