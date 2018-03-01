/**
 */
package statechart;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link statechart.Node#getName <em>Name</em>}</li>
 *   <li>{@link statechart.Node#getLabel <em>Label</em>}</li>
 *   <li>{@link statechart.Node#getType <em>Type</em>}</li>
 *   <li>{@link statechart.Node#getActivity <em>Activity</em>}</li>
 *   <li>{@link statechart.Node#getVariables <em>Variables</em>}</li>
 *   <li>{@link statechart.Node#getChildren <em>Children</em>}</li>
 *   <li>{@link statechart.Node#getFather <em>Father</em>}</li>
 *   <li>{@link statechart.Node#getActions <em>Actions</em>}</li>
 *   <li>{@link statechart.Node#getMetadata <em>Metadata</em>}</li>
 * </ul>
 *
 * @see statechart.StatechartPackage#getNode()
 * @model
 * @generated
 */
public interface Node extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see statechart.StatechartPackage#getNode_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link statechart.Node#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label</em>' attribute.
	 * @see #setLabel(String)
	 * @see statechart.StatechartPackage#getNode_Label()
	 * @model
	 * @generated
	 */
	String getLabel();

	/**
	 * Sets the value of the '{@link statechart.Node#getLabel <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label</em>' attribute.
	 * @see #getLabel()
	 * @generated
	 */
	void setLabel(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see statechart.StatechartPackage#getNode_Type()
	 * @model
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link statechart.Node#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Activity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity</em>' attribute.
	 * @see #setActivity(String)
	 * @see statechart.StatechartPackage#getNode_Activity()
	 * @model
	 * @generated
	 */
	String getActivity();

	/**
	 * Sets the value of the '{@link statechart.Node#getActivity <em>Activity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activity</em>' attribute.
	 * @see #getActivity()
	 * @generated
	 */
	void setActivity(String value);

	/**
	 * Returns the value of the '<em><b>Variables</b></em>' reference list.
	 * The list contents are of type {@link statechart.Variable}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variables</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variables</em>' reference list.
	 * @see statechart.StatechartPackage#getNode_Variables()
	 * @model
	 * @generated
	 */
	EList<Variable> getVariables();

	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link statechart.Node}.
	 * It is bidirectional and its opposite is '{@link statechart.Node#getFather <em>Father</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see statechart.StatechartPackage#getNode_Children()
	 * @see statechart.Node#getFather
	 * @model opposite="Father" containment="true"
	 * @generated
	 */
	EList<Node> getChildren();

	/**
	 * Returns the value of the '<em><b>Father</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link statechart.Node#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Father</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Father</em>' container reference.
	 * @see #setFather(Node)
	 * @see statechart.StatechartPackage#getNode_Father()
	 * @see statechart.Node#getChildren
	 * @model opposite="Children" transient="false"
	 * @generated
	 */
	Node getFather();

	/**
	 * Sets the value of the '{@link statechart.Node#getFather <em>Father</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Father</em>' container reference.
	 * @see #getFather()
	 * @generated
	 */
	void setFather(Node value);

	/**
	 * Returns the value of the '<em><b>Actions</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actions</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Actions</em>' attribute.
	 * @see #setActions(String)
	 * @see statechart.StatechartPackage#getNode_Actions()
	 * @model
	 * @generated
	 */
	String getActions();

	/**
	 * Sets the value of the '{@link statechart.Node#getActions <em>Actions</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actions</em>' attribute.
	 * @see #getActions()
	 * @generated
	 */
	void setActions(String value);

	/**
	 * Returns the value of the '<em><b>Metadata</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Metadata</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Metadata</em>' attribute.
	 * @see #setMetadata(String)
	 * @see statechart.StatechartPackage#getNode_Metadata()
	 * @model
	 * @generated
	 */
	String getMetadata();

	/**
	 * Sets the value of the '{@link statechart.Node#getMetadata <em>Metadata</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Metadata</em>' attribute.
	 * @see #getMetadata()
	 * @generated
	 */
	void setMetadata(String value);

} // Node
