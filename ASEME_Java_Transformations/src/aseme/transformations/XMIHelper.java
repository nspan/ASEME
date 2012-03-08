package aseme.transformations;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMIResource;

public class XMIHelper {
    public static EObject setXmlId(EObject obj, String id) {
        if (id != null) {
            XMIResource resource = (XMIResource) obj.eResource();
            if (resource == null) {
                throw new IllegalStateException("Object not attached to resource.");
            }
            resource.setID(obj, id);
        }
        return obj;
    }

}
