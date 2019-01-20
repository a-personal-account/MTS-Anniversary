//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package theAct.monsters.TotemBoss;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import theAct.TheActMod;
import theAct.powers.BlockFromStrengthPower;
import theAct.powers.ImmunityPower;
import theAct.vfx.TotemBeamEffect;

import java.util.Iterator;

public class ShieldOtherTotem extends AbstractTotemSpawn {
    public static final String ID = TheActMod.makeID("ShieldOtherTotem");
    private static final MonsterStrings monsterStrings;
    public static final String NAME;
    public static final String[] MOVES;
    public static final String[] DIALOG;

    public Integer secondaryEffect;


    public ShieldOtherTotem(TotemBoss boss) {
        super(NAME, ID, boss, TheActMod.assetPath("images/monsters/totemboss/totemcyan.png"));
        this.loadAnimation(TheActMod.assetPath("images/monsters/totemboss/cyan/Totem.atlas"), TheActMod.assetPath("images/monsters/totemboss/cyan/Totem.json"), 1.0F);

        AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());

        if (AbstractDungeon.ascensionLevel >= 19) {
            this.secondaryEffect = 12;
        } else if (AbstractDungeon.ascensionLevel >= 4) {
            this.secondaryEffect = 10;
        } else {
            this.secondaryEffect = 8;
        }

        this.intentType = Intent.DEFEND;
        this.powers.add(new BlockFromStrengthPower(this));


    }



    public void takeTurn() {
        breakp:

        switch (this.nextMove) {
            case 1:
                // AbstractDungeon.actionManager.addToBottom(new ChangeStateAction(this, "ATTACK"));
                AbstractDungeon.actionManager.addToBottom(new WaitAction(0.25F));
                AbstractDungeon.actionManager.addToBottom(new VFXAction(new BorderFlashEffect(Color.CYAN)));

                Integer blockBonus = 0;
                if (this.hasPower(StrengthPower.POWER_ID)){
                    blockBonus = this.getPower(StrengthPower.POWER_ID).amount;
                }
                for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {

                    if (!m.isDying && !(m instanceof TotemBoss)) {
                        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(m, this, this.secondaryEffect + blockBonus));
                    }
                }


                AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
        }
    }

    protected void getMove(int num)
    {
        this.setMove((byte)1, intentType);
    }
    static {
        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
        NAME = monsterStrings.NAME;
        MOVES = monsterStrings.MOVES;
        DIALOG = monsterStrings.DIALOG;

    }




}
