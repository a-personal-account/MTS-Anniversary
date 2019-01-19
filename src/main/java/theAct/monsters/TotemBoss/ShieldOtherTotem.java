//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package theAct.monsters.TotemBoss;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAct.TheActMod;

import java.util.Iterator;

public class ShieldOtherTotem extends AbstractTotemSpawn {
    public static final String ID = TheActMod.makeID("ShieldOtherTotem");
    private static final MonsterStrings monsterStrings;
    public static final String NAME;
    public static final String[] MOVES;
    public static final String[] DIALOG;

    public Integer secondaryEffect = 2;


    public ShieldOtherTotem(TotemBoss boss) {
        super(NAME, ID, boss);

        if (AbstractDungeon.ascensionLevel >= 19) {
            this.secondaryEffect = 10;
        } else if (AbstractDungeon.ascensionLevel >= 4) {
            this.secondaryEffect = 8;
        } else {
            this.secondaryEffect = 8;
        }

        this.intentType = Intent.DEFEND;

    }



    public void takeTurn() {
        breakp:

        switch (this.nextMove) {
            case 1:
                // AbstractDungeon.actionManager.addToBottom(new ChangeStateAction(this, "ATTACK"));
                AbstractDungeon.actionManager.addToBottom(new WaitAction(0.25F));
                Iterator var1 = AbstractDungeon.getMonsters().monsters.iterator();


                    AbstractMonster m = (AbstractMonster) var1.next();
                    if (!m.isDying && !(m instanceof TotemBoss)) {
                        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(m, this, this.secondaryEffect));
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
