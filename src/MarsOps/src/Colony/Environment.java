package Colony;

public class Environment {
    private boolean normalCondition;
    private boolean dust;
    private boolean commDisruption;

    public Environment() {
        this.normalCondition = true;
        this.dust = false;
        this.commDisruption = false;
    }
    public boolean changeToDust() {
        this.dust = true;
        this.normalCondition = false;
        return true;
    }

    public boolean changeToCommDisruption() {
        this.commDisruption = true;
        this.normalCondition = false;
        return true;
    }

    public boolean changeToNormalCondition() {
        this.normalCondition = true;
        this.dust = false;
        this.commDisruption = false;
        return true;
    }
    public boolean isNormalCondition() {
        return normalCondition;
    }

    public boolean isDust() {
        return dust;
    }

    public boolean isCommDisruption() {
        return commDisruption;
    }
}