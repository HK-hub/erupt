package xyz.erupt.upms.enums;

import lombok.Getter;
import xyz.erupt.annotation.fun.ChoiceFetchHandler;
import xyz.erupt.annotation.fun.VLModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YuePeng
 * date 2021/4/19 10:48
 */
@Getter
public enum MenuStatus {
    OPEN(1, "启用"), HIDE(2, "隐藏"), DISABLE(3, "禁用");

    private final int value;
    private final String label;

    MenuStatus(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public static class ChoiceFetch implements ChoiceFetchHandler {

        @Override
        public List<VLModel> fetch(String[] params) {
            List<VLModel> menuStatus = new ArrayList<>();
            for (MenuStatus menuTypeEnum : MenuStatus.values()) {
                menuStatus.add(new VLModel(menuTypeEnum.getValue() + "", menuTypeEnum.getLabel()));
            }
            return menuStatus;
        }

    }
}
