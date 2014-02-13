/**
 * @author OOL 1131080355959
 * @date 2014/02/06
 * @TODO TODO
 */
package ool.com.ofpa.validate;

import org.apache.commons.lang.StringUtils;

/**
 * @author 1131080355959
 *
 */
public abstract class ValidateUtil {
	
    //abstract boolean checkNullAndEmpty(String value);

    //abstract boolean checkPattern(String value);
	
    /**
     * オブジェクトnullチェック
     * @param value
     * @return not null true, null false 
     */
    protected static boolean checkNull(Object value) {
    	return value == null;
    }

    /**
     * 文字列Emptyチェック
     * @param value
     * @return empty true, not empty false 
     */
    protected static boolean checkEmpty(String value) {
		if (!StringUtils.isEmpty(value)) {
			return false;
		}
		return true;
    }

    /**
     * 文字サイズをチェック
     * @param value
     *        length
     * @return not over true/over false;
     */
    protected static boolean checkOverLength(String value, int length) {
    	if (value.length() > length) {
    		return true;
    	}
    	return false;
    }
    
    /**
    * 半角数字チェック
    * @param value
    * @return すべて半角数字 true/それ以外 false
    */
    protected static boolean checkHalfNum(String value) {
    	if (value == null || !value.matches("^[0-9]+$")) {
    		return false;
    	}
    	return true;
    }
}
