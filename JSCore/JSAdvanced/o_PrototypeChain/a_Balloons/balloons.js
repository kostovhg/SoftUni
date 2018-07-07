function balloons(input){
    class Balloon {
        constructor(color, gasWeight){
            [this.color, this.gasWeight] = [color, gasWeight];
        }
    }

    class PartyBalloon extends Balloon{
        constructor(color, gasWeight, ribbonColor, ribbonLength){
            super(color, gasWeight);
            // this._ribbon = {color: ribbonColor, length: ribbonLength};
            this.ribbon = {color: ribbonColor, length: ribbonLength};
        }

        set ribbon(ribbonObj){
            this._ribbon = ribbonObj;
        }

        get ribbon(){
            return this._ribbon;
        }
    }

    class BirthdayBalloon extends PartyBalloon {
        constructor(color, gasWeight, ribbonColor, ribbonLength, text){
            super(color, gasWeight, ribbonColor, ribbonLength);
            this.text = text;
        }
    }

    return {
        Balloon: Balloon,
        PartyBalloon: PartyBalloon,
        BirthdayBalloon: BirthdayBalloon
    }
}


