package com.pyra.krpytapplication.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.pyra.krpytapplication.R

class MoreMenuFragment : Fragment(R.layout.fragment_more_menu) {

//    lateinit var chatListAdapter: ChatListAdapter
//    var onClickButtonListener: OnClickButtonListener? = null
//    lateinit var chatListViewModel: ChatListViewModel
//    lateinit var searchViewModel: SearchViewModel
//    var loader: Dialog? = null
//    val profileViewModel: ProfileViewModel by viewModels()

//    private val loadBar by lazy {
//        showLoader(requireContext())
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        chatListViewModel = ViewModelProvider(this).get(ChatListViewModel::class.java)
//        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
//        initAdapter()
//        initListener()
//        setMenuInflater()
    }

//    private fun showSearchLayout() {
//        selectionPanel.show()
//        topLayout.hide()
//    }
//
//    private fun showNormalLayout() {
//        selectionPanel.hide()
//        topLayout.show()
//    }

//    private fun initListener() {
//
//        searchText.addTextChangedListener {
//            if (it.toString().trim() == "") {
//                chatListViewModel.getChatListAsync()
//            } else {
//                chatListViewModel.getSearchedData(it.toString())
//            }
//        }
//
//        backPressed.setOnClickListener {
//            chatListViewModel.removeSelection()
//        }
//
//        deleteChat.setOnClickListener {
//            showRemoveDialog()
//        }
//    }
//
//    @SuppressLint("SetTextI18n")
//    private fun showRemoveDialog() {
//        val dialog = getChatDeleteDialog(requireContext())
//
//        val title = dialog.findViewById<TextView>(R.id.title)
//        val checkBox = dialog.findViewById<CheckBox>(R.id.checkBox)
//        val cancel = dialog.findViewById<TextView>(R.id.cancel)
//        val delete = dialog.findViewById<TextView>(R.id.delete)
//
//        if (chatListViewModel.getSelectedCount() == 1) {
//            title.text =
//                getString(R.string.delete_chat_with) + " \"" + chatListViewModel.selectedRoomName + "\""
//        } else {
//            title.text = "Delete " + chatListViewModel.getSelectedCount() + " selected chats"
//        }
//
//        cancel.setOnClickListener {
//            dialog.dismiss()
//        }
//
//        delete.setOnClickListener {
//            chatListViewModel.deleteSelectedChats(checkBox.isChecked)
//            dialog.dismiss()
//        }
//    }
//
//    override fun onResume() {
//        super.onResume()
//        chatListViewModel.checkForBurnMessage()
//        println("LXMPP On Resume Called")
//
//        checkSubscription()
//    }
//
//    private fun checkSubscription() {
//
//        profileViewModel.getUserDeatilsResponse(
//            SharedHelper(requireContext()).kryptKey,
//            UrlHelper.GETUSERDETAILS
//        ).observe(viewLifecycleOwner) {
//
//            if (it.error == "false") {
//
//                it.data[0].subsEnddate?.let {
//
//                    val endDatedate = getFormatedDate(
//                        it,
//                        "yyyy-MM-dd'T'HH:mm:ss",
//                        "yyyy-MM-dd"
//                    )
//
//                    endDatedate?.let {
//                        if (endDatedate != "Not Updated") {
//                            val isSubscriptionEnded =
//                                isSubScriptionEnded(endDatedate, "yyyy-MM-dd")
//
//                            if (isSubscriptionEnded) {
//                                clearAllData()
//                            }
//                        }
//                    }
//
//                }
//
//            }
//
//        }
//
//    }
//
//    private fun clearAllData() {
//        val bundle = Bundle()
//        bundle.putBoolean("isSubEnded", true)
//        requireActivity().openNewTaskActivity(KryptCodeActivity::class.java, bundle)
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        onClickButtonListener = (activity as OnClickButtonListener?)
//    }
//
//    private fun initAdapter() {
//        val linearLayoutManager =
//            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
//        chatListAdapter = ChatListAdapter(requireActivity(), chatListViewModel)
//        chatLists.layoutManager = linearLayoutManager
//        chatLists.adapter = chatListAdapter
//        listener()
//
//        chatListViewModel.update.observe(viewLifecycleOwner) {
//            chatListAdapter.notifyDataSetChanged()
//            if (chatListViewModel.isMultiSelectionEnabled) {
//                showSearchLayout()
//            } else {
//                showNormalLayout()
//            }
//            selectedCount.text = chatListViewModel.getSelectedCount().toString()
//        }
//
//        chatListViewModel.chatListCount.observe(viewLifecycleOwner, Observer {
//            messageCount.text = "( $it messages )"
//        })
//
//        chatListViewModel.getChatList()
//    }
//
//    private fun listener() {
//        contactButton.setOnClickListener {
//            onClickButtonListener?.onClickListener()
//        }
//        newUser.setOnClickListener {
//            findNavController().navigate(ChatFragmentDirections.actionChatToAddContactDialog())
//        }
//    }
//
//    private fun setMenuInflater() {
//
//        val popup = PopupMenu(requireContext(), selectionMenu)
//        popup.menuInflater.inflate(R.menu.chat_message_menu, popup.menu)
//
//        popup.setOnMenuItemClickListener {
//            when (it.itemId) {
//                R.id.clearChat -> {
//                    chatListViewModel.clearAllChats()
//                }
//            }
//            return@setOnMenuItemClickListener true
//        }
//
//        selectionMenu.setOnClickListener {
//            popup.show()
//        }
//
//    }
}
