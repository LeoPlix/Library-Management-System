package bci.exceptions;

import java.io.Serial;

public class BorrowingRuleFailedException extends Exception {

    @Serial
    private static final long serialVersionUID = 202507171003L;

    private final int _userId;
    private final int _workId;
    private final int _ruleId;

    public BorrowingRuleFailedException(int userId, int workId, int ruleId) {
        _userId = userId;
        _workId = workId;
        _ruleId = ruleId;
    }

    public int getUserId() {
        return _userId;
    }

    public int getWorkId() {
        return _workId;
    }

    public int getRuleId() {
        return _ruleId;
    }
}
