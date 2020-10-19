package ec.gestion.ticket.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by baguirre on 19/10/2020.
 */
public class Utilitario implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(Utilitario.class
            .getName());

    /**
     * Poner mensaje de error en pantalla.
     *
     * @param summary
     * @param detail
     */
    public void ponerMensajeError(final String summary, final String detail) {
        FacesMessage errorMessage = new FacesMessage();
        errorMessage.setSummary(summary);
        errorMessage.setDetail(detail);
        errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage("messages", errorMessage);
    }

    protected static ClassLoader getCurrentClassLoader(
            final Object defaultObject) {

        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        if (loader == null) {
            loader = defaultObject.getClass().getClassLoader();
        }

        return loader;
    }

    private static String recuperarRecurso(final ResourceBundle bundle,
                                           final String key) {
        String mensaje = null;
        try {
            mensaje = bundle.getString(key);
        } catch (MissingResourceException e) {
            LOGGER.log(Level.INFO,
                    "No existe el recurso en el archivo de recursos", key);
            LOGGER.info(e.toString());
        }
        return mensaje;
    }

    public static String getBundle(final String key, final Object... params) {
        Locale locale = FacesContext.getCurrentInstance().getViewRoot()
                .getLocale();

        // LOGGER.trace(locale);
        ResourceBundle bundle = ResourceBundle.getBundle("ec.gestion.ticket.mensajes.messages",
                locale, getCurrentClassLoader(params));

        String mensaje = recuperarRecurso(bundle, key);

        if (mensaje == null) {
            mensaje = key;
        } else {
            if (params != null && params.length > 0) {
                MessageFormat mf = new MessageFormat(mensaje, locale);
                mensaje = mf.format(params, new StringBuffer(), null)
                        .toString();
            }

        }

        return mensaje;
    }
}
