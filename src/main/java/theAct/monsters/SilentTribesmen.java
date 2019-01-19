package theAct.monsters;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAct.TheActMod;

public class SilentTribesmen extends AbstractMonster {
    public static final String ID = TheActMod.makeID("SilentTribesmen");
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public static final String NAME = monsterStrings.NAME;
    private static final int MIN_HP = 30;
    private static final int MAX_HP = 37;
    private static final int ASC_HP_MODIFIER = 5;
    private static final int START_BLOCK_AMT = 10;
    private static final int START_BLOCK_ASC_MODIFIER = 5;
    private int blockAmt;
    public static final String STUNNED = "STUNNED";
    public static final String NOTSTUNNED = "NOTSTUNNED";

    public SilentTribesmen(float x, float y) {
        super(NAME, ID, MAX_HP, 0.0F, 10.0F, 280.0F, 280.0F, null, x, y);
        this.img = ImageMaster.loadImage(TheActMod.assetPath("/images/monsters/phrog/temp.png"));
        if (AbstractDungeon.ascensionLevel >= 7) {
            this.setHp(MIN_HP + ASC_HP_MODIFIER, MAX_HP + ASC_HP_MODIFIER);
        } else {
            this.setHp(MIN_HP, MAX_HP);
        }
        if (AbstractDungeon.ascensionLevel >= 17) {
            blockAmt = START_BLOCK_AMT + START_BLOCK_ASC_MODIFIER;
        } else {
            blockAmt = START_BLOCK_AMT;
        }
    }

    @Override
    public void changeState(String state) {
        switch(state) {
            case NOTSTUNNED:
                break;
            case STUNNED:
                setMove((byte)4, Intent.STUN);
                createIntent();
                break;
        }
    }

    @Override
    public void usePreBattleAction() {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, blockAmt));
    }

    @Override
    public void takeTurn() {

    }

    @Override
    protected void getMove(int i) {

    }
}