package tim.com.libnetwork.base;

/**
 * ${description}
 *
 * @author weiqiliu
 * @version 1.0 2020/4/20
 */
public interface Contract {
    interface BasePresenter {
    }

    interface BaseView<T> {
        void setPresenter(T presenter);
        void hideLoadingPopup();
        void displayLoadingPopup();
    }
}
